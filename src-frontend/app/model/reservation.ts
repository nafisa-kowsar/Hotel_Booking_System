export interface Reservation {
    reservationId: number;
    checkInDate: String;
    checkOutDate: String;
    numberOfAdults: number;
    numberOfChildren: number;
    totalAmount: number;
    reservationStatus: number;
    refundProcessed: boolean;
}