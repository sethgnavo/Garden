package com.spaceapps.garden;

import java.util.Date;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public interface Interval {
    //pépinière

    Date[] getPreparationPeppiniereReading(int[] dayInterval);


    Date[] getPreparationPeppiniereEntretienArrosage(int[] individualDays);


}
