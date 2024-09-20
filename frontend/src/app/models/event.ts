import { Person } from "./person";

export class Event {
    id: number;
    participants: Person[] = [];
    name: string;
    maxParticipants: number;
    startTime: Date;
    endTime: Date;
  
    constructor(id: number, participants: Person[], name: string, maxParticipants: number, startTime: Date, endTime: Date) {
      this.id = id;
      this.participants = participants;
      this.name = name;
      this.maxParticipants = maxParticipants;
      this.startTime = startTime;
      this.endTime = endTime;
    }
  }