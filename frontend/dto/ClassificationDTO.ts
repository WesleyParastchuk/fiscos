export interface ClassificationDTO { 
    id: number;
    name: string;
    description: string;
    active: boolean;
    parent: ClassificationDTO | null;
}