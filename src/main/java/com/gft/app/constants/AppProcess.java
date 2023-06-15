package com.gft.app.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public enum AppProcess {

    RECOGNITION_PROCESS("recognition.json", "1.0");

    @Getter
    private String process;

    @Getter
    private String version;
}
