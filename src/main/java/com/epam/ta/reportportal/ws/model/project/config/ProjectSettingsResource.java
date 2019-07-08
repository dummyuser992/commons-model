/*
 * Copyright 2016 EPAM Systems
 * 
 * 
 * This file is part of EPAM Report Portal.
 * https://github.com/reportportal/commons-model
 * 
 * Report Portal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Report Portal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Report Portal.  If not, see <http://www.gnu.org/licenses/>.
 */ 

package com.epam.ta.reportportal.ws.model.project.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Project settings resource output
 * 
 * @author Andrei_Ramanchuk
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectSettingsResource {

	@JsonProperty(value = "project", required = true)
	private Long projectId;

	@JsonProperty(value = "subTypes", required = true)
	private Map<String, List<IssueSubTypeResource>> subTypes;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public void setSubTypes(Map<String, List<IssueSubTypeResource>> types) {
		this.subTypes = types;
	}

	public Map<String, List<IssueSubTypeResource>> getSubTypes() {
		return subTypes;
	}
}
