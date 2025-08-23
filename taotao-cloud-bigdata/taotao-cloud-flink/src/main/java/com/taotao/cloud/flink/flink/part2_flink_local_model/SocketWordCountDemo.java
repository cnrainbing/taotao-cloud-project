/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.flink.flink.part2_flink_local_model;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/*
 * 首先在开启虚拟机等其他客户端开启8888端口
 * nc -lp 8888
 *
 * */
public class SocketWordCountDemo {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        // 设置WebUI绑定的本地端口
        conf.set(RestOptions.BIND_PORT, "8081");
        conf.set("rest.flamegraph.enabled", "true");
        // 使用配置
        StreamExecutionEnvironment env =
                StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

        // 2.读取Socket数据
        DataStreamSource<String> ds = env.socketTextStream("192.168.241.128", 8888);

        // 3.准备K,V格式数据
        SingleOutputStreamOperator<Tuple2<String, Integer>> tupleDS =
                ds.flatMap(
                                (String line, Collector<Tuple2<String, Integer>> out) -> {
                                    String[] words = line.split(",");
                                    for (String word : words) {
                                        out.collect(Tuple2.of(word, 1));
                                    }
                                })
                        .returns(Types.TUPLE(Types.STRING, Types.INT));

        // 4.聚合打印结果
        tupleDS.keyBy(tp -> tp.f0).sum(1).print();

        // 5.execute触发执行
        env.execute();
    }
}
