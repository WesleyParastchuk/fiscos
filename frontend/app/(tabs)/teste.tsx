import { useTheme } from "@react-navigation/native";
import { Text } from "react-native";
export default function HomeScreen() {
  const { colors, fonts } = useTheme();

  return <Text style={{ color: colors.text, backgroundColor: colors.primary }}>Teste Screen</Text>;
}
