import QRCodeReader from "@/components/QRCodeReader";
import { Alert } from "react-native";
import { LinkLocalStorage } from "@/data/links/LinkLocalStorage";

export default function HomeScreen() {
  const handleValidation = async (data: string): Promise<"success" | "error"> => {
    if(await LinkLocalStorage.getInstance().linkExists(data)) {
      return "error";
    }
    if (data && (data.startsWith("http://") || data.startsWith("https://"))) {
      return "success";
    }
    return "error";
  };

  const handleSuccess = async (link: string) => {
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
      successLabel="Link Válido!"
    />
  );
}
