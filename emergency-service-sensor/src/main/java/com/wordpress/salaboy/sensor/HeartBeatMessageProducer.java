/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.salaboy.sensor;

import com.wordpress.salaboy.messaging.MessageFactory;
import com.wordpress.salaboy.model.messages.patient.HeartBeatMessage;
import java.util.Date;
import org.hornetq.api.core.HornetQException;

/**
 *
 * @author esteban
 */
public class HeartBeatMessageProducer {
    
    private SensorHeartBeatParser dataParser;

    public HeartBeatMessageProducer(SensorHeartBeatParser dataParser) {
        this.dataParser = dataParser;
    }
    
    public void onRawDataReceived(String data) throws Exception{
        this.onHeartBeatReceived(dataParser.getHeartBeatValue(data));
    }
    
    public void onHeartBeatReceived(double heartBeat) throws Exception{
        try {
            MessageFactory.sendMessage(new HeartBeatMessage(0L, 0L, heartBeat, new Date()));
        } catch (HornetQException ex) {
            throw new Exception("Unable to add Heart Beat message into queue!", ex);
        }
    }
    
}
