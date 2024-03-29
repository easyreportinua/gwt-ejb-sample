/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.sample.dynatable.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mits.core.client.Person;

import java.util.List;

/**
 * The interface for the RPC server endpoint that provides school calendar
 * information for clients that will be calling asynchronously.
 */
public interface SchoolCalendarServiceAsync {
    void getPeople(int startIndex, int maxCount, AsyncCallback<List<Person>> async);
}
