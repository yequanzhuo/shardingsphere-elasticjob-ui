/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.elasticjob.lite.ui.domain;

import lombok.Data;
import org.apache.shardingsphere.elasticjob.tracing.event.JobExecutionEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "JOB_EXECUTION_LOG",schema = "ELASTIC_JOB")
public class JobExecutionLog {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "JOB_NAME")
    private String jobName;

    @Column(name = "TASK_ID")
    private String taskId;

    @Column(name = "HOSTNAME")
    private String hostname;

    @Column(name = "IP")
    private String ip;

    @Column(name = "SHARDING_ITEM")
    private Integer shardingItem;

    @Column(name = "EXECUTION_SOURCE")
    private String executionSource;

    @Column(name = "FAILURE_CAUSE")
    private String failureCause;

    @Column(name = "IS_SUCCESS")
    private Boolean isSuccess;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "COMPLETE_TIME")
    private Date completeTime;

    /**
     * JobExecutionLog convert to JobExecutionEvent.
     *
     * @return JobExecutionEvent entity
     */
    public JobExecutionEvent toJobExecutionEvent() {
        return new JobExecutionEvent(
                id,
                hostname,
                ip,
                taskId,
                jobName,
                JobExecutionEvent.ExecutionSource.valueOf(executionSource),
                shardingItem,
                startTime,
                completeTime,
                isSuccess,
                failureCause
        );
    }

}
