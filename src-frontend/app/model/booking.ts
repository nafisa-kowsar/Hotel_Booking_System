import { BookedRoom } from "./bookedRoom";

export interface Booking {
    userId: number | null;
    checkInDate: string;
    checkOutDate: string;
    bookedRoom: BookedRoom[];
}

