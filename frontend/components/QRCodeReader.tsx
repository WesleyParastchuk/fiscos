import React, { useState, useEffect } from "react";
import {
  StyleSheet,
  Text,
  View,
  Dimensions,
  ActivityIndicator,
} from "react-native";
import { CameraView, Camera } from "expo-camera";
import { Feather } from "@expo/vector-icons";
import { useIsFocused } from "@react-navigation/native";

type QRCodeReaderProps = {
  onScanSuccess: (data: string) => void;
  onValidate: (data: string) => Promise<"success" | "error">;
  instructionText?: string;
  successLabel?: string;
  errorLabel?: string;
  errorDescription?: string;
};

export default function QRCodeReader({
  onScanSuccess,
  onValidate,
  instructionText = "Aponte a câmera para o QR Code",
  successLabel = "Sucesso!",
  errorLabel = "Inválido",
  errorDescription = "Este QR Code não é válido.",
}: QRCodeReaderProps) {
  const [hasPermission, setHasPermission] = useState<boolean | null>(null);
  const [scanned, setScanned] = useState<boolean>(false);
  const [qrData, setQrData] = useState<string | null>(null);
  const [validationResult, setValidationResult] = useState<
    "success" | "error" | null
  >(null);

  const isFocused = useIsFocused();

  useEffect(() => {
    const getCameraPermissions = async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setHasPermission(status === "granted");
    };

    getCameraPermissions();
  }, []);

  useEffect(() => {
    if (scanned) {
      const timer = setTimeout(() => {
        setScanned(false);
        setQrData(null);
        setValidationResult(null);
      }, 2000);

      return () => clearTimeout(timer);
    }
  }, [scanned]);

  const handleBarCodeScanned = async ({ data }: { data: string }) => {
    if (scanned) {
      return;
    }
    setScanned(true);
    setQrData(data);
    const result = await onValidate(data);
    setValidationResult(result);

    if (result === "success") {
      onScanSuccess(data);
    }
  };

  if (hasPermission === null) {
    return (
      <View style={styles.permissionContainer}>
        <ActivityIndicator size="large" color="#ffffff" />
        <Text style={styles.permissionText}>
          Solicitando permissão da câmera...
        </Text>
      </View>
    );
  }
  if (hasPermission === false) {
    return (
      <View style={styles.permissionContainer}>
        <Text style={styles.permissionText}>Sem acesso à câmera</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      {isFocused && hasPermission && (
        <CameraView
          onBarcodeScanned={handleBarCodeScanned}
          barcodeScannerSettings={{
            barcodeTypes: ["qr"],
          }}
          style={StyleSheet.absoluteFillObject}
        />
      )}

      <View style={styles.scanAreaContainer}>
        <View style={[styles.corner, styles.topLeft]} />
        <View style={[styles.corner, styles.topRight]} />
        <View style={[styles.corner, styles.bottomLeft]} />
        <View style={[styles.corner, styles.bottomRight]} />
      </View>

      {scanned && validationResult && (
        <View style={styles.resultOverlay}>
          {validationResult === "success" && (
            <>
              <Feather name="check-circle" size={48} color="#4CAF50" />
              <Text style={[styles.resultLabel, styles.successText]}>
                {successLabel}
              </Text>
              <Text style={styles.resultData} numberOfLines={3}>
                {qrData}
              </Text>
            </>
          )}

          {validationResult === "error" && (
            <>
              <Feather name="x-circle" size={48} color="#F44336" />
              <Text style={[styles.resultLabel, styles.errorText]}>
                {errorLabel}
              </Text>
              <Text style={styles.resultDataSmall}>{errorDescription}</Text>
            </>
          )}
        </View>
      )}

      {!scanned && (
        <View style={styles.instructionOverlay}>
          <Text style={styles.instructionText}>{instructionText}</Text>
        </View>
      )}
    </View>
  );
}

const { width } = Dimensions.get("window");
const squareSize = width * 0.7;
const cornerThickness = 4;
const cornerSize = 40;
const cornerRadius = 20;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "black",
  },
  permissionContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#000",
  },
  permissionText: {
    color: "white",
    fontSize: 16,
    marginTop: 10,
  },
  scanAreaContainer: {
    width: squareSize,
    height: squareSize,
    justifyContent: "center",
    alignItems: "center",
  },
  corner: {
    width: cornerSize,
    height: cornerSize,
    position: "absolute",
    borderColor: "white",
  },
  topLeft: {
    top: 0,
    left: 0,
    borderTopWidth: cornerThickness,
    borderLeftWidth: cornerThickness,
    borderTopLeftRadius: cornerRadius,
  },
  topRight: {
    top: 0,
    right: 0,
    borderTopWidth: cornerThickness,
    borderRightWidth: cornerThickness,
    borderTopRightRadius: cornerRadius,
  },
  bottomLeft: {
    bottom: 0,
    left: 0,
    borderBottomWidth: cornerThickness,
    borderLeftWidth: cornerThickness,
    borderBottomLeftRadius: cornerRadius,
  },
  bottomRight: {
    bottom: 0,
    right: 0,
    borderBottomWidth: cornerThickness,
    borderRightWidth: cornerThickness,
    borderBottomRightRadius: cornerRadius,
  },
  resultOverlay: {
    position: "absolute",
    bottom: 50,
    left: 20,
    right: 20,
    backgroundColor: "rgba(0, 0, 0, 0.85)",
    padding: 20,
    borderRadius: 15,
    alignItems: "center",
  },
  resultLabel: {
    fontSize: 20,
    fontWeight: "bold",
    marginTop: 8,
  },
  successText: {
    color: "#4CAF50",
  },
  errorText: {
    color: "#F44336",
  },
  resultData: {
    fontSize: 14,
    color: "#4a90e2",
    marginVertical: 10,
    paddingHorizontal: 10,
    textAlign: "center",
  },
  resultDataSmall: {
    fontSize: 13,
    color: "white",
    marginVertical: 8,
    textAlign: "center",
  },
  instructionOverlay: {
    position: "absolute",
    top: 60,
    backgroundColor: "rgba(0, 0, 0, 0.7)",
    paddingHorizontal: 20,
    paddingVertical: 10,
    borderRadius: 20,
  },
  instructionText: {
    color: "white",
    fontSize: 14,
    textAlign: "center",
  },
});
