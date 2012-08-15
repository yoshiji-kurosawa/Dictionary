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

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import kurosawa.dictionary.main.client.DictionaryService;
import kurosawa.dictionary.main.client.DictionaryServiceResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DictionaryServiceImpl extends RemoteServiceServlet implements DictionaryService {

	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(DictionaryServiceImpl.class);

	DictionaryModel dictionaryModel;
	@Autowired
	public void setDictionaryModel(DictionaryModel dictionaryModel) {
		log.info("dictionaryModel:" + dictionaryModel);
		this.dictionaryModel = dictionaryModel;
	}

	public void init() throws ServletException
	{
	    try {
	        log.info("WebApplicationContextUtils");
	        // applicationContext.xmlに定義されているBean群とこのサーブレットを
	        // アノテーションに従って Autowireする
	        WebApplicationContextUtils
	            .getRequiredWebApplicationContext(getServletContext())
	                .getAutowireCapableBeanFactory().autowireBean(this);
	        log.info("init WebApplicationContextUtils end");
	    }
	    catch (IllegalStateException ex) {
	        // この例外が起こる場合web.xmlに設定が足りない
	        throw new ServletException(
	            "Couldn't get Spring's WebApplicationContext. " +
	            "Please check whether ContextLoaderListener exists " +
	            "in your web.xml.");
	    }
	}

	public DictionaryServiceResponse search(String english) {
		DictionaryServiceResponse dsr;
		try {
			DictionaryModelResponse dmr = dictionaryModel.search(english);
			dsr = new DictionaryServiceResponse(dmr.getFunction(), dmr.getMessage(), dmr.getEnglish(), dmr.getJapanese());
		} catch (Exception ex) {
			dsr = new DictionaryServiceResponse("add", "検索失敗:" + ex.getMessage(), english, "");
		}
		return dsr;
	}

	public DictionaryServiceResponse add(String english, String japanese) {
		DictionaryServiceResponse dsr;
		try {
			DictionaryModelResponse dmr = dictionaryModel.add(english, japanese);
			dsr = new DictionaryServiceResponse(dmr.getFunction(), dmr.getMessage(), dmr.getEnglish(), dmr.getJapanese());
		} catch (Exception ex) {
			dsr = new DictionaryServiceResponse("add", "登録失敗:" + ex.getMessage(), english, japanese);
		}
		return dsr;
	}

	public DictionaryServiceResponse delete(String english) {
		DictionaryServiceResponse dsr;
		try {
			DictionaryModelResponse dmr = dictionaryModel.delete(english);
			dsr = new DictionaryServiceResponse(dmr.getFunction(), dmr.getMessage(), dmr.getEnglish(), dmr.getJapanese());
		} catch (Exception ex) {
			dsr = new DictionaryServiceResponse("add", "削除失敗:" + ex.getMessage(), english, "");
		}
		return dsr;
	}

}
