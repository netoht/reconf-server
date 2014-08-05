/*
 *    Copyright 1996-2014 UOL Inc
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
package reconf.server.reader;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;
import reconf.server.domain.*;

@RestController
@RequestMapping(value="/crud", produces="application/json", consumes={"application/vnd.reconf-v1+text", "text/plain"})
public class CrudService {

    @Autowired private PropertyRepository properties;

    @RequestMapping(value="/product/{prod}/component/{comp}/property/{prop}", method=RequestMethod.PUT)
    @Transactional
    public ResponseEntity<String> upsert(@PathVariable("prod") String product, @PathVariable("comp") String component, @PathVariable("prop") String property, @RequestParam(value="instance", required=false) String hostName) {
        PropertyKey key = new PropertyKey(product, component, property, hostName);
        Property fromDB = properties.findOne(key);
        if (fromDB != null) {
            fromDB.setValue(String.valueOf(System.currentTimeMillis()));
        } else {
            Property toDB = new Property(key, String.valueOf(System.currentTimeMillis()));
            properties.save(toDB);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
