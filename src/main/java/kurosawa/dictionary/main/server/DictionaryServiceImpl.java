/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kurosawa.dictionary.main.server;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.springframework.web.context.support.WebApplicationContextUtils;

import kurosawa.dictionary.main.client.DictionaryService;
import kurosawa.dictionary.main.client.Response;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DictionaryServiceImpl extends RemoteServiceServlet implements DictionaryService {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(DictionaryServiceImpl.class.getName());
	
	public void init() throws ServletException
	{
	    try {
	        logger.info("init WebApplicationContextUtils start");
	        // applicationContext.xmlに定義されているBean群とこのサーブレットを
	        // アノテーションに従って Autowireする
	        WebApplicationContextUtils
	            .getRequiredWebApplicationContext(getServletContext())
	                .getAutowireCapableBeanFactory().autowireBean(this);
	        logger.info("init WebApplicationContextUtils end");
	    }
	    catch (IllegalStateException ex) {
	        // この例外が起こる場合web.xmlに設定が足りない
	        throw new ServletException(
	            "Couldn't get Spring's WebApplicationContext. " +
	            "Please check whether ContextLoaderListener exists " +
	            "in your web.xml.");
	    }
	}

	public Response search(String english) {
		Response response = new Response("search","検索成功",english,"日本語");
		return response;
	}

	public Response add(String english, String japanese) {
		Response response = new Response("add","登録成功",english,japanese);
		return response;
	}

	public Response delete(String english) {
		Response response = new Response("delete","削除成功",english,"日本語");
		return response;
	}

}
