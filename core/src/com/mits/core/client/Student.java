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
package com.mits.core.client;

import javax.persistence.*;

/**
 * Holds relevant data for a Student type Person. This class is intended to be
 * serialized in RPC calls.
 */
@Entity
@DiscriminatorValue("Student")
public class Student extends Person {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Schedule classSchedule = new Schedule();

    public Schedule getClassSchedule() {
        return classSchedule;
    }

    @Override
    public String getSchedule(boolean[] daysFilter) {
        return classSchedule.getDescription(daysFilter);
    }
}
