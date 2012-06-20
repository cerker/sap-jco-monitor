package de.akquinet.jbosscc.sapjco;

import com.sap.conn.jco.JCo;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

public class MonitoringData implements Serializable
{
    private static final long serialVersionUID = -2403138958014741653L;

    private String destinationName;
    private JCoDestination destination;

    public String getDestinationName()
    {
        return destinationName;
    }

    public void setDestinationName( String destinationName )
    {
        this.destinationName = destinationName;
    }

    public JCoDestination getDestination() throws JCoException
    {
        return destination;
    }

    public String chooseDestination()
    {
        try
        {
            destination = JCoDestinationManager.getDestination( destinationName );
        }
        catch ( JCoException e )
        {
            List<String> destinationIDs = JCo.getDestinationIDs();

            FacesContext.getCurrentInstance()
                    .addMessage( null, new FacesMessage(
                            "Destination " + destinationName + " not found. Try any of these: " + destinationIDs ) );
        }
        return null;
    }
}
