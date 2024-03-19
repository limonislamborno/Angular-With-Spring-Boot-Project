export interface TransferData {
    fromAccountNumber: string ;
    fromFirstName: string;
    toAccountNumber: string;
    toFirstName: string;
    transferAmount: number;
    [key: string]: string | number; // Index signature
  }
  