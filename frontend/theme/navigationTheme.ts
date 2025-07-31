// src/theme/navigationThemes.ts
import { Colors } from "@/constants/Colors";
import { Theme as NavigationTheme } from "@react-navigation/native";

const fonts: {
  regular: NavigationTheme["fonts"]["regular"];
  medium: NavigationTheme["fonts"]["medium"];
  bold: NavigationTheme["fonts"]["bold"];
  heavy: NavigationTheme["fonts"]["heavy"];
} = {
  regular: { fontFamily: "System", fontWeight: "400" },
  medium: { fontFamily: "System", fontWeight: "500" },
  bold: { fontFamily: "System", fontWeight: "700" },
  heavy: { fontFamily: "System", fontWeight: "900" },
};

export const CustomLightTheme: NavigationTheme = {
  dark: false,
  colors: Colors.light,
  fonts,
};

export const CustomDarkTheme: NavigationTheme = {
  dark: true,
  colors: Colors.dark,
  fonts,
};
