import AsyncStorage from "@react-native-async-storage/async-storage";
import { ILinkStorage } from "./ILinkStorage";

export class LinkLocalStorage implements ILinkStorage {
  private static readonly STORAGE_KEY = "@scanned_links";
  private static instance: LinkLocalStorage;

  private constructor() {}

  public static getInstance(): LinkLocalStorage {
    if (!LinkLocalStorage.instance) {
      LinkLocalStorage.instance = new LinkLocalStorage();
    }
    return LinkLocalStorage.instance;
  }

  private async getStoredLinks(): Promise<string[]> {
    try {
      const rawData = await AsyncStorage.getItem(LinkLocalStorage.STORAGE_KEY);
      return rawData ? JSON.parse(rawData) : [];
    } catch (error) {
      console.error(`Erro ao obter dados do AsyncStorage: ${error}`);
      return [];
    }
  }

  private async saveStoredLinks(links: string[]): Promise<void> {
    try {
      await AsyncStorage.setItem(
        LinkLocalStorage.STORAGE_KEY,
        JSON.stringify(links)
      );
    } catch (error) {
      console.error(`Erro ao salvar dados no AsyncStorage: ${error}`);
    }
  }

  public async getAllLinks(): Promise<string[]> {
    return await this.getStoredLinks();
  }

  public async setLink(value: string): Promise<void> {
    try {
      const links = await this.getStoredLinks();
      if (links.includes(value)) {
        console.warn(`Link já existe: ${value}`);
        return;
      }
      const updatedLinks = [value, ...links];
      await this.saveStoredLinks(updatedLinks);
    } catch (error) {
        console.error(`Erro ao definir o link: ${error}`);
    }
  }

  public async removeLink(linkToRemove: string): Promise<void> {
    try {
      const links = await this.getStoredLinks();
      const updatedLinks = links.filter(link => link !== linkToRemove);

      if (links.length === updatedLinks.length) {
        console.warn(`Link não encontrado para remoção: ${linkToRemove}`);
        return;
      }

      await this.saveStoredLinks(updatedLinks);
    } catch (error) {
      console.error(`Erro ao remover link do AsyncStorage: ${error}`);
    }
  }

  public async clearLinks(): Promise<void> {
    try {
      await AsyncStorage.removeItem(LinkLocalStorage.STORAGE_KEY);
    } catch (error) {
      console.error(`Erro ao limpar links do AsyncStorage: ${error}`);
    }
  }

  public async linkExists(value: string): Promise<boolean> {
    const links = await this.getStoredLinks();
    return links.includes(value);
  }
}