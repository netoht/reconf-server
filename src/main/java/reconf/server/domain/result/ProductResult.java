/*
 *    Copyright 2013-2014 ReConf Team
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package reconf.server.domain.result;

import java.net.*;
import java.util.*;
import reconf.server.domain.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProductResult {

    private String product;
    private String desc;
    private Link link;
    private List<String> errors;

    private ProductResult(Product arg) {
        this.product = arg.getName();
        this.desc = arg.getDescription();
    }

    public ProductResult(Product arg, String baseURL) {
        this(arg);
        this.link = new Link(URI.create(baseURL + getUriOf(arg)), "self");
    }

    public ProductResult(Product arg, List<String> errors) {
        this(arg);
        this.errors = errors;
    }

    private static String getUriOf(Product arg) {
        return "/product/" + arg.getName();
    }

    public String getProduct() {
        return product;
    }

    public String getDesc() {
        return desc;
    }

    public Link getLink() {
        return link;
    }

    public List<String> getErrors() {
        return errors;
    }
}
