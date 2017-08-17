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

import java.util.Set;

class TransactionEndedEvent extends SagaEvent {

  private final SagaResponse response;

  TransactionEndedEvent(long sagaId, SagaRequest request) {
    this(sagaId, request, SagaResponse.EMPTY_RESPONSE);
  }

  TransactionEndedEvent(long sagaId, SagaRequest request, SagaResponse response) {
    super(sagaId, request);
    this.response = response;
  }

  @Override
  public void gatherTo(
      Set<SagaRequest> hangingTransactions,
      Set<SagaRequest> abortedTransactions,
      Set<Operation> completedTransactions,
      Set<Operation> completedCompensations) {

    completedTransactions.add(payload().transaction());
    hangingTransactions.remove(payload());
  }

  @Override
  public String toString() {
    return "TransactionEndedEvent{id="
        + payload().id()
        + ", sagaId=" + sagaId
        + ", operation="
        + payload().transaction()
        + "}";
  }
}