package com.murk.dk021.core.utils;

import com.murk.dk021.core.process.UpdateClassificatorProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class UpdateManager
{
    private ThreadPoolTaskExecutor updatePool;
    private UpdateClassificatorProcess updateProcess;

    @Autowired
    public UpdateManager(ThreadPoolTaskExecutor updatePool) {
        this.updatePool = updatePool;
    }

    public boolean executeIfFree(UpdateClassificatorProcess updateClassificatorProcess)
    {
        boolean result;
        if (updateProcess == null || isLastUpdateFinish())
        {
            this.updateProcess = updateClassificatorProcess;
            updatePool.execute(updateClassificatorProcess);
            result = true;
        } else
            {
                result = false;
            }
        return result;
    }

    private boolean isLastUpdateFinish()
    {
        return updateProcess.isFinish();
    }

    public void shutdown() {
        updatePool.shutdown();
    }
}
