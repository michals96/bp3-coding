import {AppPage} from './app.po';
import {browser, by, element, logging} from 'protractor';

describe('E2E', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    let elementFinder = element(by.css('title'));
    expect(elementFinder).toBeTruthy();
  });

  afterEach(async () => {
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});


describe('E2E', function () {
  it('App should have correct title', function () {
    browser.ignoreSynchronization = true;
    browser.get('http://localhost:4200/');
    browser.driver.getTitle().then(function (pageTitle) {
      expect(pageTitle).toEqual('Frontend');
    });
  });
});
