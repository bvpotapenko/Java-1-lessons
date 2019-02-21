package ru.bvpotapenko.se.j1.oop;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Employee[] persArray = new Employee[5];
        persArray[0] = new Employee("Ivanov Ivan",
                "Engineer",
                "ivivan@mailbox.com",
                "892312312",
                30,
                new BigDecimal(30000) );
        persArray[1] = new Employee("Sdf Ivan",
                "Engineer",
                "ivivan@mailbox.com",
                "892312312",
                30,
                new BigDecimal(430000) );
        persArray[2] = new Employee("F asd Ivan",
                "Engineer",
                "ivivan@mailbox.com",
                "892312312",
                30,
                new BigDecimal(130000) );
        persArray[3] = new Employee("DAasdov Ivan",
                "Engineer",
                "ivivan@mailbox.com",
                "892312312",
                30,
                new BigDecimal(330000) );
        persArray[4] = new Employee("Iv v Ivan",
                "Engineer",
                "ivivan@mailbox.com",
                "892312312",
                30,
                new BigDecimal(303000) );
        for(Employee e: persArray){
            System.out.println(e);
        }
    }

}
