/*
 * Copyright 2021 4Paradigm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com._4paradigm.openmldb.java_sdk_test.command.chain;



import com._4paradigm.openmldb.java_sdk_test.command.OpenmlDBCommandFactory;
import com._4paradigm.openmldb.java_sdk_test.entity.FesqlResult;
import com._4paradigm.openmldb.test_common.bean.FEDBInfo;

import java.util.List;

public class QueryHandler extends AbstractSQLHandler{
    @Override
    public boolean preHandle(String sql) {
        return sql.split("\\s+")[0].equalsIgnoreCase("select");
    }

    @Override
    public FesqlResult onHandle(FEDBInfo fedbInfo, String dbName, String sql) {
        FesqlResult fesqlResult = new FesqlResult();
        List<String> result = OpenmlDBCommandFactory.runNoInteractive(fedbInfo,dbName,sql);
        System.out.println("select result = " + result);
        fesqlResult.setMsg(result.get(0));
        fesqlResult.setOk(true);
        return fesqlResult;
    }
}