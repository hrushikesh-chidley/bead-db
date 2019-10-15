package com.bead.server;

import com.bead.common.DependencyInjector;
import com.bead.common.Inject;
import com.bead.common.RunOptions;
import com.bead.core.DbConfigurer;
import com.bead.core.DbRunner;

public class BeadDbServer {
	
	private static final RunOptions DEFAULT_OPTION = RunOptions.RUN;
	
	@Inject
	private DbRunner dbRunner;
	
	@Inject
	private DbConfigurer dbConfigurer;

	public static void main(final String[] args) {
		final DependencyInjector di = DependencyInjector.getInstance();
		di.inject();
		final BeadDbServer server = di.getBean(BeadDbServer.class);
		
		switch(server.getOption(args)) {
		case CONFIGURE :
			server.configure();
			break;
		case HELP :
			server.showHelp();
			break;
		default :
			server.run();
			break;
		}
	}
	
	private void run() {
		dbRunner.run();
	}

	private void showHelp() {
		// TODO Auto-generated method stub
	}

	private void configure() {
		dbConfigurer.configure();
	}

	private RunOptions getOption( final String [] args ) {
		if( args.length > 0 ) {
			if("--configure".equalsIgnoreCase(args[0])){
				return RunOptions.CONFIGURE;
			} else {
				return RunOptions.HELP;
			}
		}
		return DEFAULT_OPTION;
	}

}
