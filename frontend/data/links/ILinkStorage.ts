export interface ILinkStorage {
  getAllLinks(): Promise<string[]>;
  setLink(key: string, value: string): Promise<void>;
  removeLink(key: string): Promise<void>;
  linkExists(value: string): Promise<boolean>;
  clearLinks(): Promise<void>;
}
