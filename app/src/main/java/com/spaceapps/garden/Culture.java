package com.spaceapps.garden;

import java.util.Date;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class Culture {
    public static Date[] getPreparationPepiniereReading(Date today, int start, int end) {

        //timestamp at the end of the reading 1 day = 86400000ms
        long endTimestamp = today.getTime() + (end * 86400000);
        Date endDate = new Date(endTimestamp);
        return new Date[]{today, endDate};
    }

    /*public static Date[] getPreparationPepiniereEntretienArrosage(Date today, int[] individualDays) {

    }*/

}
