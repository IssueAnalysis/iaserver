package com.issue.iaserver.extractor.server;

import com.issue.iaserver.extractor.focus.Focus;

import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/16 3:41 下午
 */
public interface FocusInfoService {
    List<Focus> getAllFocus();

    ExtractMessage addFocus(Focus focus);

    ExtractMessage updateFocus(Focus focus);
}
