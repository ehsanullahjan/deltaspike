/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.deltaspike.jsf.impl.listener.request;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.core.util.ClassDeactivationUtils;

import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextWrapper;

class DeltaSpikeFacesContextWrapper extends FacesContextWrapper
{
    private final FacesContext wrappedFacesContext;

    private BeforeAfterJsfRequestBroadcaster beforeAfterJsfRequestBroadcaster;

    DeltaSpikeFacesContextWrapper(FacesContext wrappedFacesContext)
    {
        this.wrappedFacesContext = wrappedFacesContext;

        setCurrentInstance(this);
    }

    /**
     * Broadcasts the {@link org.apache.deltaspike.jsf.api.listener.request.AfterJsfRequest} event
     * {@inheritDoc}
     */
    @Override
    public void release()
    {
        if (!this.wrappedFacesContext.getApplication().getResourceHandler().isResourceRequest(this.wrappedFacesContext))
        {
            broadcastAfterJsfRequestEvent();
        }

        wrappedFacesContext.release();
    }

    private void broadcastAfterJsfRequestEvent()
    {
        lazyInit();
        if (this.beforeAfterJsfRequestBroadcaster != null)
        {
            this.beforeAfterJsfRequestBroadcaster.broadcastAfterJsfRequestEvent(this);
        }
    }

    private void lazyInit()
    {
        if (this.beforeAfterJsfRequestBroadcaster == null)
        {
            if (ClassDeactivationUtils.isActivated(BeforeAfterJsfRequestBroadcaster.class))
            {
                this.beforeAfterJsfRequestBroadcaster =
                        BeanProvider.getContextualReference(BeforeAfterJsfRequestBroadcaster.class, true);
            }
        }
    }

    @Override
    public FacesContext getWrapped()
    {
        return this.wrappedFacesContext;
    }
}
