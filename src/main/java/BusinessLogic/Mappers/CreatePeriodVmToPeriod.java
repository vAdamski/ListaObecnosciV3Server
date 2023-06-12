package BusinessLogic.Mappers;

import Shared.Entities.Period;
import Shared.ViewModels.CreatePeriodVm;

public class CreatePeriodVmToPeriod {
    public static Period map(CreatePeriodVm createPeriodVm) {
        return new Period(
                createPeriodVm.getDate(),
                createPeriodVm.getStartTime(),
                createPeriodVm.getEndTime(),
                createPeriodVm.getGroupId(),
                createPeriodVm.getSubjectId()
        );
    }
}
