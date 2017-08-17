/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.saga.core;

import static io.servicecomb.saga.core.Compensation.SAGA_END_COMPENSATION;
import static io.servicecomb.saga.core.Transaction.SAGA_END_TRANSACTION;

public class SagaEndTask implements SagaTask {

  private final long sagaId;
  private final SagaLog sagaLog;

  public SagaEndTask(long sagaId, SagaLog sagaLog) {
    this.sagaId = sagaId;
    this.sagaLog = sagaLog;
  }

  @Override
  public long sagaId() {
    return sagaId;
  }

  @Override
  public void commit() {
    sagaLog.offer(new SagaEndedEvent(sagaId, this));
  }

  @Override
  public void compensate() {
  }

  @Override
  public void abort(Exception e) {

  }

  @Override
  public Transaction transaction() {
    return SAGA_END_TRANSACTION;
  }

  @Override
  public Compensation compensation() {
    return SAGA_END_COMPENSATION;
  }

  @Override
  public String serviceName() {
    return "Saga";
  }

  @Override
  public String id() {
    return "saga-end";
  }

  @Override
  public String type() {
    return "nop";
  }
}