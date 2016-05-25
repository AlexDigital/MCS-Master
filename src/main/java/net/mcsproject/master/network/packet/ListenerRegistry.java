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

package net.mcsproject.master.network.packet;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.mcsproject.master.logging.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ListenerRegistry {

    private static ListenerRegistry instance;
    public static ListenerRegistry getInstance() {
        if(instance == null) instance = new ListenerRegistry();
        return instance;
    }

    private Table<Class<?>, PacketListener, Method> packetListener = HashBasedTable.create();

    private ListenerRegistry() {}

    public void register(PacketListener listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (method.getAnnotationsByType(PacketHandler.class).length == 0) {
                continue;
            }
            packetListener.put(method.getParameterTypes()[0], listener, method);
        }
    }

    public void callEvent(Packet packet) {
        if (!packetListener.containsRow(packet.getClass())) {
            return;
        }
        packetListener.row(packet.getClass()).forEach((listener, method) -> {
            try {
                method.invoke(listener, packet);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Log.error(ex.getMessage());
            }
        });
    }


}
