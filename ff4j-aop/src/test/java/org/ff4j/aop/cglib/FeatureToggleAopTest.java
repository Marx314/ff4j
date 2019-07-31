package org.ff4j.aop.cglib;

/*-
 * #%L
 * ff4j-aop
 * %%
 * Copyright (C) 2013 - 2019 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.ff4j.FF4j;
import org.ff4j.aop.GreetingService;
import org.junit.jupiter.api.Test;

import net.sf.cglib.proxy.Enhancer;

/**
 * En utilisant les superclass des enhancer on peut changer le comportement
 * au runtime.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class FeatureToggleAopTest {
    
    @Test
    public void testAOP() {
        // Given
        FF4j ff4j = new FF4j();
        ToggledFeatureInterceptor interceptor = new ToggledFeatureInterceptor(ff4j);
        Enhancer e = new Enhancer();
        e.setSuperclass(GreetingService.class);
        e.setCallback(interceptor);
        // When
        GreetingService service = (GreetingService) e.create();
        System.out.println(service.greetings("Cedrick"));
        // Then
        e.setSuperclass(GreetingService.class);
        service = (GreetingService) e.create();
        System.out.println(service.greetings("Cedrick"));
    }

}