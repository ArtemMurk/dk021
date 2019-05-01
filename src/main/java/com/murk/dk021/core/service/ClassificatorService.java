package com.murk.dk021.core.service;

import com.murk.dk021.core.to.ClassificatorTO;
import com.murk.dk021.core.to.UpdateInfoTO;

import java.util.Set;

public interface ClassificatorService {
    ClassificatorTO get(String code);
    Set<ClassificatorTO> getNodes(String code);
    UpdateInfoTO update();
}
