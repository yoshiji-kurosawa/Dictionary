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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {
	private TextBox txbEnglish;
	private TextBox txbJapanese;
	private Button btnSearch;
	private Button btnAdd;
	private Button btnDelete;
	private Label lblMessage;

	private DictionaryServiceAsync dictionaryService = DictionaryService.Util
			.getInstance();
	private HorizontalPanel horizontalPanel;

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setStyleName("rootPanel");

		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 5, 5);
		verticalPanel.setSize("244px", "162px");

		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setWidth("100%");

		HTML htmlNewHtml = new HTML("<h3>辞書</h3>", true);
		htmlNewHtml.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel.add(htmlNewHtml);
		htmlNewHtml.setWidth("59px");

		Label lblNewLabel = new Label("20120814Dictionary");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		horizontalPanel.add(lblNewLabel);

		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1
				.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.add(horizontalPanel_1);

		Label lblNewLabel_1 = new Label("英語");
		horizontalPanel_1.add(lblNewLabel_1);
		lblNewLabel_1.setSize("48px", "");

		txbEnglish = new TextBox();
		horizontalPanel_1.add(txbEnglish);

		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		horizontalPanel_2
				.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.add(horizontalPanel_2);

		Label lblNewLabel_2 = new Label("日本語");
		horizontalPanel_2.add(lblNewLabel_2);
		lblNewLabel_2.setSize("48px", "");

		txbJapanese = new TextBox();
		horizontalPanel_2.add(txbJapanese);

		HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_3);

		btnSearch = new Button();
		btnSearch.setHTML("検索");
		horizontalPanel_3.add(btnSearch);
		btnSearch.setText("検索");

		btnAdd = new Button("登録");
		btnAdd.setText("登録");
		horizontalPanel_3.add(btnAdd);

		btnDelete = new Button("削除");
		horizontalPanel_3.add(btnDelete);
		
		btnSearch.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				txbJapanese.setText("");
				lblMessage.setText("");
				dictionaryService.search(txbEnglish.getText(),
						new AsyncCallback<DictionaryServiceResponse>() {

							public void onFailure(Throwable caught) {
								Window.alert("検索失敗:" + caught.getMessage());								
							}

							public void onSuccess(DictionaryServiceResponse response) {
								txbJapanese.setText(response.getJapanese());
								lblMessage.setText(response.getMessage());
							}
							
						});

			}
		});

		btnAdd.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lblMessage.setText("");
				dictionaryService.add(txbEnglish.getText(), txbJapanese.getText(),
						new AsyncCallback<DictionaryServiceResponse>() {

							public void onFailure(Throwable caught) {
								Window.alert("登録失敗:" + caught.getMessage());								
							}

							public void onSuccess(DictionaryServiceResponse response) {
								lblMessage.setText(response.getMessage());
							}
							
						});

			}
		});

		btnDelete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				txbJapanese.setText("");
				lblMessage.setText("");
				dictionaryService.delete(txbEnglish.getText(),
						new AsyncCallback<DictionaryServiceResponse>() {

							public void onFailure(Throwable caught) {
								Window.alert("削除失敗:" + caught.getMessage());								
							}

							public void onSuccess(DictionaryServiceResponse response) {
								txbJapanese.setText(response.getJapanese());
								lblMessage.setText(response.getMessage());
							}
							
						});

			}
		});

		HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_4);

		lblMessage = new Label("");
		horizontalPanel_4.add(lblMessage);
	}
}
