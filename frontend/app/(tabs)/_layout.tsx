import { router, Tabs } from "expo-router";
import React from "react";
import { MaterialIcons } from "@expo/vector-icons";
import { TouchableOpacity } from "react-native";

export default function TabLayout() {
  return (
    <Tabs
      screenOptions={{
        tabBarActiveTintColor: "#007AFF",
        tabBarInactiveTintColor: "gray",
      }}
    >
      <Tabs.Screen
        name="index"
        options={{
          title: "Escanear QR",
          headerShown: false,
          tabBarIcon: ({ color, size }) => (
            <MaterialIcons name="qr-code-scanner" size={size} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="history"
        options={{
          title: "Notas pendentes",
          headerTitle: "Notas Pendentes",
          tabBarIcon: ({ color, size }) => (
            <MaterialIcons name="monetization-on" size={size} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="invoices/index"
        options={{
          title: "Notas fiscais",
          headerTitle: "Notas Fiscais",
          tabBarIcon: ({ color, size }) => (
            <MaterialIcons name="receipt" size={size} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="invoices/[id]"
        options={{
          title: "Detalhes da Nota",
          headerTitle: "Detalhes da Nota",
          tabBarIcon: ({ color, size }) => (
            <MaterialIcons name="receipt" size={size} color={color} />
          ),
          href: null,
          headerLeft: () => (
            <TouchableOpacity
              onPress={() => router.push("/invoices")}
              style={{ margin: 16 }}
            >
              <MaterialIcons name="arrow-back" size={24} color="#007AFF" />
            </TouchableOpacity>
          ),
        }}
      />
      <Tabs.Screen
        name="categories/index"
        options={{
          title: "Categorias",
          headerTitle: "Categorias",
          tabBarIcon: ({ color, size }) => (
            <MaterialIcons name="category" size={size} color={color} />
          )
        }}
      />
      <Tabs.Screen
        name="categories/[id]"
        options={{
          title: "Editar ID Categoria",
          headerTitle: "Editar ID Categoria",
          href: null,
          headerLeft: () => (
            <TouchableOpacity
              onPress={() => router.back()}
              style={{ margin: 16 }}
            >
              <MaterialIcons name="arrow-back" size={24} color="#007AFF" />
            </TouchableOpacity>
          ),
        }}
      />
    </Tabs>
  );
}
