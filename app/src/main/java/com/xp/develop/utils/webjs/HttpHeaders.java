/*
 * Copyright (C)  Justson(https://github.com/Justson/AgentWeb)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xp.develop.utils.webjs;

import android.support.v4.util.ArrayMap;

import java.util.Map;


/**
 * @author cenxiaozhong
 * @date 2017/7/5
 * @since 2.0.0
 */
@Deprecated
public class HttpHeaders {


    public static HttpHeaders create() {
        return new HttpHeaders();
    }

    private Map<String, String> mHeaders = null;

    HttpHeaders() {
        mHeaders = new ArrayMap<>();
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public void additionalHttpHeader(String k, String v) {
        mHeaders.put(k, v);
    }

    public void removeHttpHeader(String k) {
        mHeaders.remove(k);
    }

    public boolean isEmptyHeaders() {
        return mHeaders == null || mHeaders.isEmpty();
    }


    @Override
    public String toString() {
        return "HttpHeaders{" +
                "mHeaders=" + mHeaders +
                '}';
    }
}