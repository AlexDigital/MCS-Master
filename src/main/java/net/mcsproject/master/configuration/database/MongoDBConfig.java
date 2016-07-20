/*
 *     MCS - Minecraft Cloud System
 *     Copyright (C) 2016
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.mcsproject.master.configuration.database;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class MongoDBConfig extends DatabaseConfig {

	@Getter(AccessLevel.PUBLIC)
	@Setter(AccessLevel.PUBLIC)
	private String ip;

	@Getter(AccessLevel.PUBLIC)
	@Setter(AccessLevel.PUBLIC)
	private String port;

	@Setter(AccessLevel.PUBLIC)
	@Getter(AccessLevel.PUBLIC)
	private String db;

	@Setter(AccessLevel.PUBLIC)
	@Getter(AccessLevel.PUBLIC)
	private String user;

	@Setter(AccessLevel.PUBLIC)
	@Getter(AccessLevel.PUBLIC)
	private String pw;
}
