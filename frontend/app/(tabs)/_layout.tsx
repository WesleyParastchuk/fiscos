import { Tabs } from 'expo-router';
import React from 'react';
import { MaterialIcons } from '@expo/vector-icons';

export default function TabLayout() {
  return (
    <Tabs
      screenOptions={{
        tabBarActiveTintColor: '#007AFF',
        tabBarInactiveTintColor: 'gray',
      }}
    >
      <Tabs.Screen
        name="index"
        options={{
          title: 'Escanear QR',
          headerShown: false,
          tabBarIcon: ({ color, size }) => (
            <MaterialIcons name="qr-code-scanner" size={size} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="history"
        options={{
          title: 'Notas pendentes',
          headerTitle: 'Notas Pendentes',
          tabBarIcon: ({ color, size }) => (
            <MaterialIcons name="monetization-on" size={size} color={color} />
          ),
        }}
      />
    </Tabs>
  );
}