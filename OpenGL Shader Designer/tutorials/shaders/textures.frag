uniform sampler2D tex;

void main(void)
{
	vec4 value = texture2D(tex, vec2(gl_TexCoord[0]));
	gl_FragColor = value;
}
