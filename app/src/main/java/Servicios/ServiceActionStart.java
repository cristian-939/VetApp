package Servicios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by cryst on 06/06/2017.
 */

public class ServiceActionStart extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
            Intent servicio = new Intent(context,ServiceRecordatorios.class);
            context.startService(servicio);
        }
    }
}
