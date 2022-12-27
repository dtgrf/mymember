package co.yixiang.modules.quartz.task;

import co.yixiang.modules.monitor.service.VisitsService;
import org.springframework.stereotype.Component;

/**
 * @author member
 * @date 2022-10-16
 */
@Component
public class VisitsTask {

    private final VisitsService visitsService;

    public VisitsTask(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    public void run() {
        visitsService.save();
    }
}
