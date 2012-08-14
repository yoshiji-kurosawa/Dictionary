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
package kurosawa.dictionary.main.client;

import kurosawa.dictionary.client.GreetingService;
import kurosawa.dictionary.client.GreetingServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {
	private TextBox txbSend;
	private Button btnSend;
	private Label lblReceive;

	private DictionaryServiceAsync dictionaryService = DictionaryService.Util.getInstance();

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel);

		txbSend = new TextBox();
		verticalPanel.add(txbSend);

		btnSend = new Button();
		verticalPanel.add(btnSend);
		btnSend.setText("send");

		lblReceive = new Label("");
		verticalPanel.add(lblReceive);
		btnSend.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				dictionaryService.send(txbSend.getText(), new AsyncCallback<String>(){

					public void onFailure(Throwable caught) {
						Window.alert("送信エラー");
					}

					public void onSuccess(String receive) {
						lblReceive.setText(receive);
					}});

			}
		});
	}
}
