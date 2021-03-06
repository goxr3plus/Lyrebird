/*
 *     Lyrebird, a free open-source cross-platform twitter client.
 *     Copyright (C) 2017-2018, Tristan Deloche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package moe.lyrebird.model.credits.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO used to map the creditted works.
 */
public final class CredittedWork {

    @JsonProperty
    private final String title;

    @JsonProperty
    private final CredittedWorkAuthor author;

    @JsonProperty
    private final CredittedWorkLicensor licensor;

    @JsonProperty
    private final CredittedWorkLicense license;

    @JsonCreator
    public CredittedWork(
            @JsonProperty("title") final String title,
            @JsonProperty("author") final CredittedWorkAuthor author,
            @JsonProperty("licensor") final CredittedWorkLicensor licensor,
            @JsonProperty("license") final CredittedWorkLicense license
    ) {
        this.title = title;
        this.author = author;
        this.licensor = licensor;
        this.license = license;
    }

    public String getTitle() {
        return title;
    }

    public CredittedWorkAuthor getAuthor() {
        return author;
    }

    public CredittedWorkLicensor getLicensor() {
        return licensor;
    }

    public CredittedWorkLicense getLicense() {
        return license;
    }

}
