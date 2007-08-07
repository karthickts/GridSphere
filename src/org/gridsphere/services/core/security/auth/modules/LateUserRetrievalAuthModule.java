package org.gridsphere.services.core.security.auth.modules;

import org.gridlab.gridsphere.services.core.security.auth.modules.LoginAuthModule;
import org.gridsphere.services.core.security.auth.modules.impl.UserDescriptor;

import java.util.Map;

/**
 * @author <a href="mailto:docentt@man.poznan.pl">Tomasz Kuczynski</a>, PSNC
 * @version $Id$
 */
public interface LateUserRetrievalAuthModule extends LoginAuthModule {
    public UserDescriptor checkAuthentication(Map parametersMap, Map attributesMap) throws Exception;
}
