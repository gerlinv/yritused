export class Person {
    id: number;
    firstName: string;
    lastName: string;
    identificationCode: number;
  
    constructor(id: number, firstName: string, lastName: string, identificationCode: number) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.identificationCode = identificationCode;
    }
  }