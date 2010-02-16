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
 * Hold relevant data for Person. This class is meant to be serialized in RPC
 * calls.
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "discriminator", length = 10)
public abstract class Person extends SuperEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "discriminator", updatable = false, insertable = false)
    private String discriminator;

    private String description = "DESC";

    @Column(name = "name")
    private String name;

    public Person() {
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public abstract String getSchedule(boolean[] daysFilter);

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

}
