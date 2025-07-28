import QRCodeReader from "@/components/QRCodeReader";
import { Alert } from "react-native";
import { useRef } from "react";
import { LinkLocalStorage } from "@/data/links/LinkLocalStorage";

export default function HomeScreen() {
  const blockScanRef = useRef<boolean>(false);
  const debounceRef = useRef<ReturnType<typeof setTimeout> | null>(null);

  const handleValidation = async (data: string): Promise<"success" | "error"> => {
    if (blockScanRef.current) return "error";

    const alreadyExists = await LinkLocalStorage.getInstance().linkExists(data);
    if (alreadyExists) return "error";

    if (data && (data.startsWith("http://") || data.startsWith("https://"))) {
      return "success";
    }

    return "error";
  };

  const handleSuccess = async (link: string) => {
    // Bloqueia novas leituras por 1,5s
    blockScanRef.current = true;
    if (debounceRef.current) clearTimeout(debounceRef.current);
    debounceRef.current = setTimeout(() => {
      blockScanRef.current = false;
    }, 1500);

    try {
      await LinkLocalStorage.getInstance().setLink(link);
    } catch (e) {
      Alert.alert("Erro", "Não foi possível salvar o link.");
    }
  };

  return (
    <QRCodeReader
      onScanSuccess={handleSuccess}
      onValidate={handleValidation}
      successLabel="Link válido!"
    />
  );
}
