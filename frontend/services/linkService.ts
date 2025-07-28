import axios from "axios";
import { API_BASE_URL } from "@/constants/Api";
import { LinkLocalStorage } from "@/data/links/LinkLocalStorage";

export const loadLinks = async (): Promise<string[]> => {
  return await LinkLocalStorage.getInstance().getAllLinks();
};

export const removeLink = async (link: string): Promise<void> => {
  return await LinkLocalStorage.getInstance().removeLink(link);
};

export const clearLinks = async (): Promise<void> => {
  return await LinkLocalStorage.getInstance().clearLinks();
};

export const linkExists = async (link: string): Promise<boolean> => {
  return await LinkLocalStorage.getInstance().linkExists(link);
};

export const saveLink = async (link: string): Promise<void> => {
  await LinkLocalStorage.getInstance().setLink(link);
};

export const sendLinksToBackend = async (links: string[], userId: string) => {
  const response = await axios.post(`${API_BASE_URL}/qrcode`, {
    links,
    userId,
  });
  return response;
};
