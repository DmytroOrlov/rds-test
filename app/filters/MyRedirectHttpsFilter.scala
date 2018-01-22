package filters

import com.typesafe.scalalogging.StrictLogging
import javax.inject.{Inject, Singleton}
import play.api.libs.streams.Accumulator
import play.api.mvc.{EssentialAction, Results}
import play.filters.https.{RedirectHttpsConfiguration, RedirectHttpsFilter}

@Singleton
class MyRedirectHttpsFilter @Inject()(config: RedirectHttpsConfiguration)
  extends RedirectHttpsFilter(config)
    with StrictLogging {
  override def apply(next: EssentialAction): EssentialAction = EssentialAction { req =>
    logger.debug(req.headers.toString)
    if (req.headers.get("X-Forwarded-Proto").contains("http"))
      Accumulator.done(Results.Redirect(createHttpsRedirectUrl(req), config.redirectStatusCode))
    else next(req)
  }
}
