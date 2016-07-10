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

package net.mcsproject.master.database.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.Getter;
import net.mcsproject.master.configuration.database.MongoDBConfig;
import net.mcsproject.master.database.Database;
import net.mcsproject.master.database.mongodb.relations.UserRelation;
import net.mcsproject.master.database.objects.User;
import org.bson.Document;

public class MongoDB implements Database {
    @Getter(AccessLevel.PUBLIC)
    private MongoClient client;
    @Getter(AccessLevel.PUBLIC)
    private MongoCollection<Document> users;
    @Getter(AccessLevel.PUBLIC)
    private MongoDatabase database;

    @Getter(AccessLevel.PUBLIC)
    private static MongoDB instance;
    private MongoDBConfig dbConfig;
    private UserRelation userRelation;

    public MongoDB(MongoDBConfig mongoDBConfig) {
        super();
        dbConfig = mongoDBConfig;
        instance = this;
    }

    @Override
    public void createUser(User user) throws Exception {
        userRelation.addUser(user);
    }

    @Override
    public boolean checkUser(User user) throws Exception {
        return userRelation.checkUser(user);
    }

    @Override
    public void install() {
        load();
    }

    @Override
    public void load() {
        client = 		 new MongoClient(new MongoClientURI("mongodb://"+
                dbConfig.getUser()
                + ":" + dbConfig.getPw()
                + "@" +  dbConfig.getIp()
                + ":" + dbConfig.getPort()));
        database = client.getDatabase(dbConfig.getDb());
        users = database.getCollection("user");
        userRelation = new UserRelation();
    }

    @Override
    public void close() {
        instance = null;
    }
}
