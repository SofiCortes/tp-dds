package procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;

public class ManejadorDeProcesos {

	private static ManejadorDeProcesos singleton = null;
	private ScheduledExecutorService scheduler;
	private List<Proceso> procesosEjecutados;
	
	public List<Proceso> getProcesosEjecutados(){
		
		return this.procesosEjecutados;
		
	}

	public void setScheduler(ScheduledExecutorService scheduler) {
		this.scheduler = scheduler;
	}

	private ManejadorDeProcesos() {
		this.scheduler = new ScheduledThreadPoolExecutor(1);
		this.procesosEjecutados = new ArrayList<Proceso>();
	}

	public static ManejadorDeProcesos getInstance() {
		if (singleton == null) {
			singleton = new ManejadorDeProcesos();
		}

		return singleton;
	}

	public void configurarProceso(Accion accion, Double frecuencia, DateTime fechaYhoraDeEjecucion, CriterioDeManejoDeError criterioError) {
		
		Proceso procesoAEjecutar = new Proceso(accion, frecuencia, fechaYhoraDeEjecucion, criterioError);
		DateTime fechaYhoraProcesoParaEjecutar = procesoAEjecutar.getFechaYhoraDeEjecucion();
	
		if (procesoAEjecutar.getFrecuenciaEnHoras() == 0) {

			scheduler.schedule(procesoAEjecutar,
					fechaYhoraProcesoParaEjecutar.getMillis() - DateTime.now().getMillis(), TimeUnit.MILLISECONDS);

		} else {
			scheduler.scheduleAtFixedRate(procesoAEjecutar,
					fechaYhoraProcesoParaEjecutar.getMillis() - DateTime.now().getMillis(),
					(long) (procesoAEjecutar.getFrecuenciaEnHoras() * 60 * 60 * 1000), TimeUnit.MILLISECONDS);
		}
	}

	public void agregarProcesoEjecutado(Proceso proceso) {
		this.procesosEjecutados.add(proceso);		
	}

	public void vaciarListaProcesosEjecutados() {
		this.procesosEjecutados.clear();
		
	}

	public ScheduledExecutorService getScheduler() {
		return scheduler;
	}

}
