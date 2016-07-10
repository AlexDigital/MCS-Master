package net.mcsproject.master.database.mongodb.relations;

import com.mongodb.client.model.Filters;
import net.mcsproject.master.database.mongodb.MongoDB;
import net.mcsproject.master.database.objects.User;
import org.bson.Document;

/**
 * Created by Steve on 10.07.2016.
 */
public class UserRelation {

    public void addUser (User user) {
        MongoDB.getInstance().getUsers().insertOne(new Document().append("Name",user.getUsername().toLowerCase())
                .append("Password", user.getPasswordHash()));
    }

    public boolean checkUser (User user) {
        try {
            return MongoDB.getInstance().getUsers().find(Filters.eq("Name", user.getUsername()))
                    .filter(Filters.eq("Password", user.getPasswordHash())).first() != null;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
